import styles from "./NavLogoutButton.module.css"
import {useAuth} from "../../context/AuthContext.tsx";
import {deleteCookie} from "../../global/cookie/cookie.ts";
import {jwtTokenName} from "../../global/constant/jwt.ts";

export default function NavLogoutButton() {

  const auth = useAuth();

  const handleLogoutButton = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    auth.updateAuthContext(null, [], false);
    deleteCookie(jwtTokenName);
    location.reload();
  }

  return (
    <button className={styles.navButton} onClick={(e) => handleLogoutButton(e)}>
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="size-6">
        <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 9V5.25A2.25 2.25 0 0 1 10.5 3h6a2.25 2.25 0 0 1 2.25 2.25v13.5A2.25 2.25 0 0 1 16.5 21h-6a2.25 2.25 0 0 1-2.25-2.25V15m-3 0-3-3m0 0 3-3m-3 3H15"/>
      </svg>
      <span>로그아웃</span>
    </button>
  )
}