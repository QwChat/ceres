export interface CommonResult {
    status: boolean;
    message: string;
}
export interface UserInfo {
    nickname: string;
    email: string;
    userId: string;
}
export interface RegisterRequest {
    nickname: string;
    password: string;
    email: string;
}
export interface RegisterResponse {
    result: CommonResult;
    userId: number;
}
export interface LoginRequest {
    nickname: string;
    password: string;
}
export interface LoginResponse {
    result: CommonResult;
    userInfo: UserInfo;
}
export declare function Register(payload: Partial<RegisterRequest>): Promise<RegisterResponse>;
export declare function Login(payload: Partial<LoginRequest>): Promise<LoginResponse>;
declare const _default: {
    Register: typeof Register;
    Login: typeof Login;
};
export default _default;
