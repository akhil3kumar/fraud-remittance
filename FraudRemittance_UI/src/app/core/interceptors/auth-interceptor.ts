import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, switchMap, throwError } from 'rxjs';
import { AuthService } from '../services/auth/auth-service';
import { Router } from '@angular/router';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');
  const router = inject(Router);
  if (token) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  return next(req).pipe(
    catchError((error) => {
      if (error.status == 401) {
        return inject(AuthService)
          .refreshToken()
          .pipe(
            switchMap((response) => {
              localStorage.setItem('token', response.accessToken);
              localStorage.setItem('refreshToken', response.refreshToken);

              const clonedRequest = req.clone({
                setHeaders: {
                  Authorization: `Bearer ${response.accessToken}`,
                },
              });
              return next(clonedRequest);
            }),
            catchError((refreshError) => {
              localStorage.clear();
              router.navigate(['/login']);
              return throwError(() => refreshError);
            })
          );
      }
      return throwError(() => error);
    })
  );
};
