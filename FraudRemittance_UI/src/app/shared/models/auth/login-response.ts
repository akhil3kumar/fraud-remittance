export interface LoginResponse {
  userId: number;
  name: string;
  email: string;
  phoneNumber: string;
  role: string;
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  loginTime: string;
}
