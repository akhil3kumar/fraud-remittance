import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { RegisterationForm } from '../../../user/registeration-form/registeration-form';
import { Observable, throwError } from 'rxjs';
import { RegistrationRequest } from '../../../shared/models/auth/registration-request';
import { RegistrationResponse } from '../../../shared/models/auth/register-response';
import { LoginRequest } from '../../../shared/models/auth/login-request';
import { LoginResponse } from '../../../shared/models/auth/login-response';
import { RefreshTokenResponse } from '../../../shared/models/auth/refreshToken-response';
import { errorContext } from 'rxjs/internal/util/errorContext';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}
  private apiUrl = environment.apiUrl + `/users`;

  registerUser(request: RegistrationRequest): Observable<RegistrationResponse> {
    return this.http.post<RegistrationResponse>(`${this.apiUrl}/register`, request);
  }

  loginUser(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, request);
  }

  refreshToken(): Observable<RefreshTokenResponse> {
    const refreshToken: string | null = localStorage.getItem('refreshToken');
    if (!refreshToken) {
      this.logout();
      return throwError(() => new Error('Refresh token not found'));
    }
    return this.http.post<RefreshTokenResponse>(`${this.apiUrl}/refresh-token`, {
      refreshToken,
    });
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('refreshToken');
  }
}
