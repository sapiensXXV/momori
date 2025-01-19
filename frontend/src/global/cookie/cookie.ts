export function getCookieValue(searchName: string): string | null {
  const cookies = document.cookie.split(';');
  for (const cookie of cookies) {
    const [name, value] = cookie.trim().split('=');
    if (name === searchName) {
      return decodeURIComponent(value);
    }
  }
  return null;
}

export function deleteCookie(cookieName: string): void {
  document.cookie = cookieName +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}