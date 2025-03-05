import styles from "./NavbarTitle.module.css";
import {Link} from "react-router-dom";

export default function NavbarTitle() {
  return (
    <>
      <Link className={styles.link} to="/">
        <img className={styles.logo} src="/logo7.svg" />
        <span className={styles.title}>모모리</span>
      </Link>
    </>
  )
}