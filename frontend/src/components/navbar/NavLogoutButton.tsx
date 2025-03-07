import styles from "./NavLogoutButton.module.css"
import {useAuth} from "../../context/AuthContext.tsx";
import {deleteCookie} from "../../global/cookie/cookie.ts";
import {jwtTokenName, refreshTokenName} from "../../global/constant/jwt.ts";
import {useNavigate} from "react-router-dom";

export default function NavLogoutButton() {

  const auth = useAuth();
  const navigate = useNavigate();

  const handleLogoutButton = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();

    auth.updateAuthContext(null, [], "", false);
    localStorage.removeItem("auth"); // 로컬 스토리지에서 인증 정보 삭제
    deleteCookie(jwtTokenName);
    deleteCookie(refreshTokenName);
    navigate('/', { replace: true });
  }

  return (
    <button className={styles.navButton} onClick={(e) => handleLogoutButton(e)}>
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" className="size-6">
        <path strokeLinecap="round" strokeLinejoin="round" d="M8.25 9V5.25A2.25 2.25 0 0 1 10.5 3h6a2.25 2.25 0 0 1 2.25 2.25v13.5A2.25 2.25 0 0 1 16.5 21h-6a2.25 2.25 0 0 1-2.25-2.25V15m-3 0-3-3m0 0 3-3m-3 3H15"/>
      </svg>
      <span>로그아웃</span>
    </button>
  )
}