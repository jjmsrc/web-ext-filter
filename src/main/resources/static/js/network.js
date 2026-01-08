const API = {
    EXTS: '/exts', EXT: (id) => `/exts/${id}`
};

async function request(url, {
    method = 'GET', body = null, headers = {}
        } = {}) {

    const options = {
        method, headers: {
            'Content-Type': 'application/json', ...headers
        }
    };

    if (body) {
        options.body = JSON.stringify(body);
    }

    const res = await fetch(url, options);

    // 공통 에러 처리
    if (!res.ok) {
        const error = await res.json().catch(() => null);
        throw {
            status: res.status, message: error?.message || '요청 실패'
        };
    }

    // 204 No Content
    if (res.status === 204) return null;

    return res.json();
}

const fileExtensionApi = {
    getAll() {
        return request(API.EXTS);
    },

    check(id) {
        return request(API.EXT(id), {
            method: 'PATCH'
        });
    },

    create(name) {
        return request(API.EXTS, {
            method: 'POST',
            body: { name }
        });
    },

    remove(id) {
        return request(API.EXT(id), {
            method: 'DELETE'
        });
    }

};

