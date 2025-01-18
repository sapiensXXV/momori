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