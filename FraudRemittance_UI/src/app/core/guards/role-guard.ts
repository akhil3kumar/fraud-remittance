import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

export const roleGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  const token = localStorage.getItem('token');

  if (!token) {
    return router.createUrlTree(['/login']);
  }

  try {
    const decoded: any = jwtDecode(token);

    const allowedRoles = route.data?.['roles'] as string[];

    if (!allowedRoles || allowedRoles.length === 0) {
      return true;
    }

    if (allowedRoles.includes(decoded.role)) {
      return true;
    }

    return router.createUrlTree(['/unauthorized']);
  } catch {
    localStorage.clear();
    return router.createUrlTree(['/login']);
  }
};
