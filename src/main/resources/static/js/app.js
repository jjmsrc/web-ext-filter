
const customExtMax = 200;
let customExtCnt = 0;

const onCheck = (id, name) => {
    console.log("check: " + id);

    if (name.length > 20) {
        window.alert("길이는 20 이하여야 합니다.");
        return;
    }

    // TODO: 등록 요청

    const ret = {id: 11, name: name, fixed: 0, checked: 1};

    return ret;
}

const onUncheck = (id) => {

    // TODO: 해제 요청

    console.log("uncheck: " + id);
}

const fetchData = () => {

    const exts = [
        {id: 1, name: "bat", fixed: 1, checked: 0},
        {id: 2, name: "cmd", fixed: 1, checked: 0},
        {id: 3, name: "com", fixed: 1, checked: 0},
        {id: 4, name: "cpl", fixed: 1, checked: 1},
        {id: 5, name: "exe", fixed: 1, checked: 0},
        {id: 6, name: "scr", fixed: 1, checked: 0},
        {id: 7, name: "js", fixed: 1, checked: 0},
        {id: 8, name: "myext1", fixed: 0, checked: 1},
        {id: 9, name: "myext2", fixed: 0, checked: 1},
        {id: 10, name: "myext2", fixed: 0, checked: 1},
    ];

    return exts;

}

const init = () => {

    const exts = fetchData();

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

    const addFixedExt = (ext) => {
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.id = `cb-${ext.name}`;
        checkbox.name = ext.name;
        checkbox.checked = ext.checked;
        checkbox.addEventListener("change", (e) => {
            const checked = e.target.checked;
            if (checked) {
                onCheck(ext.id, ext.name);
            } else {
                onUncheck(ext.id);
            }
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
            onUncheck(ext.id);
            updateCustomCnt(--customExtCnt);
            wrapper.remove();
        });

        wrapper.appendChild(btn);
        customExt.appendChild(wrapper);

        cntCustom++;
    }

    exts.forEach(ext => {
        if (ext.fixed) {
            addFixedExt(ext);
        } else {
            addCustomExt(ext);
        }
    });

    updateCustomCnt(customExtCnt = cntCustom);

    const inputExt = document.getElementById("inp-ext");
    const addBtn = document.getElementById("btn-add-ext");

    addBtn.addEventListener("click", () => {
        const value = inputExt.value.trim();
        if (!value) return;

        inputExt.value = "";   // 입력값 초기화

        // 입력은 영어 대소문자와 숫자만 허용
        if (!/^[a-zA-Z0-9]+$/.test(value)) {
            alert("영어 대소문자와 숫자만 입력 가능합니다.");
            return;
        }

        if (customExtCnt >= 200) {
            window.alert("커스텀 확장자는 최대 200개까지 추가 가능합니다.");
            return;
        }

        // 객체 추가
        const ext = onCheck(0, value);

        if (!ext) return;

        addCustomExt(ext);
        updateCustomCnt(++customExtCnt);

    });

}

init();
