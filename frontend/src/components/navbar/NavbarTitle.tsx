import styles from "./NavbarTitle.module.css";
import {Link} from "react-router-dom";

export default function NavbarTitle() {
  return (
    <>
      <Link className={styles.link} to="/">
        <img className={styles.logo} src="/logo3.svg" />
        <span className={styles.title}>풀리고</span>
      </Link>
    </>
  )
}