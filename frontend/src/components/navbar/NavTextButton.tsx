import styles from "./NavTextButton.module.css"
import {Link} from "react-router-dom";

export default function NavLoginButton() {
  return (
    <>
      <Link className={styles.link} to="/login">
        <button className={styles.navButton}>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
               stroke="currentColor"
               className="size-6">
            <path stroke-linecap="round" stroke-linejoin="round"
                  d="M15.75 9V5.25A2.25 2.25 0 0 0 13.5 3h-6a2.25 2.25 0 0 0-2.25 2.25v13.5A2.25 2.25 0 0 0 7.5 21h6a2.25 2.25 0 0 0 2.25-2.25V15M12 9l-3 3m0 0 3 3m-3-3h12.75"/>
          </svg>
          <span>로그인</span>
        </button>
      </Link>
    </>
  )
}