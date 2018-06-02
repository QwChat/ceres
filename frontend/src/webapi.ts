import axios from "axios";

function getAPIurl(method: string) {
    const HOST = "127.0.0.1:8080";
    return  `//${HOST}/api/${method}`.toLocaleLowerCase()
}

export default function Webapi<T>(method: string, payload: any) {
    return axios.post<T>(getAPIurl(method), payload, {}).then(res => {
        return res.data
    });
}