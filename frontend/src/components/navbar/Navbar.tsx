import styles from "./Navbar.module.css"
import {Link} from "react-router-dom";
import NavLoginButton from "./NavTextButton.tsx";
import NoticeButton from "./NoticeButton.tsx";

export default function Navbar() {
  return (
    <>
      <nav className={styles.main}>
        <section>
          <Link className={styles.link} to="/">
            <img className={styles.logo} src="/logo3.svg" />
            <span className={styles.title}>풀리고</span>
          </Link>
        </section>
        <section className={styles.buttons}>
          <NoticeButton />
          <NavLoginButton />
        </section>
      </nav>
    </>
  )
}