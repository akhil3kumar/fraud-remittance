import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  const token = localStorage.getItem('token');
  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  try {
    const decoded: any = jwtDecode(token);
    const currentTime = Date.now() / 1000;
    if (decoded.exp < currentTime) {
      localStorage.clear();
      return router.createUrlTree(['/login']);
    }
    return true;
  } catch {
    localStorage.clear();

    return router.createUrlTree(['/login']);
  }
};
