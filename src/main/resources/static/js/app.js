
import { fileExtensionApi } from './network.js';

const customExtMax = 200;
let customExtCnt = 0;

const fetchData = () => {
    return fileExtensionApi.getAll();
}

const onCheck = (id) => {
    return fileExtensionApi.check(id);
}

const onCreate  = (name) => {
    if (name.length > 20) {
        window.alert("길이는 20 이하여야 합니다.");
        return;
    }

    return fileExtensionApi.create(name);
}

const onRemove = (id) => {
    return fileExtensionApi.remove(id);
}

const handleError = (err) => {
    window.alert(err.message);
    console.log(err);
}

const init = () => {

    const extsPromise = fetchData();

    const fixedExt = document.getElementById("fixed-ext");
    const customExt = document.getElementById("custom-ext");
    const customCount = document.getElementById("custom-count");

    fixedExt.replaceChildren();
    customExt.replaceChildren();
    const cntMax = customExtMax;
    let cntCustom = 0;

    const updateCustomCnt = (c) => {
        customCount.textContent = `${c}/${cntMax}`;
    }

    // 1초에 5번 이상 체크를 누를 경우 경고 화면 팝업
    const MAX_CLICKS = 5;
    const MAX_TIME = 1000;
    let clickCount = 0;
    let timer = null;
    const handleMaxClick = () => {
        clickCount++;

        if (!timer) {
            timer = setTimeout(() => {
                clickCount = 0;
                timer = null;
            }, MAX_TIME);
        }

        if (clickCount >= MAX_CLICKS) {
            clickCount = 0;
            clearTimeout(timer);
            timer = null;
            return false;
        }

        return true;
    }

    const addFixedExt = (ext) => {
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.id = `cb-${ext.name}`;
        checkbox.name = ext.name;
        checkbox.checked = ext.checked;
        checkbox.addEventListener("change", (e) => {
            if (!handleMaxClick()) {
                alert("너무 빠르게 클릭했습니다.");
                return;
            }
            onCheck(ext.id).then((ext = {id: 0, name: "", fixed: 0, checked: 0}) => {
                if (!ext.id) window.alert("유효하지 않은 요청입니다.");
                e.target.checked = ext.checked;
            }).catch(handleError);
        });

        const label = document.createElement("label");
        label.htmlFor = checkbox.id;
        label.textContent = ext.name;

        const wrapper = document.createElement("span");
        wrapper.className = "checkbox-item";

        wrapper.appendChild(checkbox);
        wrapper.appendChild(label);

        fixedExt.appendChild(wrapper);
    }

    const addCustomExt = (ext) => {
        const wrapper = document.createElement("span");
        wrapper.textContent = ext.name;
        wrapper.className = "chip";

        const btn = document.createElement("button");
        btn.className = "chip-remove";
        btn.type = "button";
        btn.textContent = "x";
        btn.addEventListener("click", (e) => {
            onRemove(ext.id).then(() => {
                updateCustomCnt(--customExtCnt);
                wrapper.remove();
            }).catch(handleError);
        });

        wrapper.appendChild(btn);
        customExt.appendChild(wrapper);

        cntCustom++;
    }

    extsPromise.then(exts => {
        exts.forEach(ext => {
            if (ext.fixed) {
                addFixedExt(ext);
            } else {
                addCustomExt(ext);
            }
        });
        updateCustomCnt(customExtCnt = cntCustom);
    }).catch(handleError);

    const inputExt = document.getElementById("inp-ext");
    const addBtn = document.getElementById("btn-add-ext");

    addBtn.addEventListener("click", () => {
        const name = inputExt.value.trim();
        if (!name) return;

        inputExt.value = "";   // 입력값 초기화

        // 입력은 영어 대소문자와 숫자만 허용
        if (!/^[a-zA-Z0-9]+$/.test(name)) {
            alert("영어 대소문자와 숫자만 입력 가능합니다.");
            return;
        }

        if (customExtCnt >= 200) {
            window.alert("커스텀 확장자는 최대 200개까지 추가 가능합니다.");
            return;
        }

        // 객체 추가
        const extPromise = onCreate(name);

        extPromise.then((ext) => {
            addCustomExt(ext);
            updateCustomCnt(++customExtCnt);
        }).catch(handleError);

    });

}

init();
