import styles from "./Navbar.module.css"
import NavLoginButton from "./NavLoginButton.tsx";
import NoticeButton from "./NoticeButton.tsx";
import NavbarTitle from "./NavbarTitle.tsx";
import ContactButton from "./ContactButton.tsx";
import {useAuth} from "../../context/AuthContext.tsx";
import NavLogoutButton from "./NavLogoutButton.tsx";
import NavCreateQuizButton from "./NavCreateQuizButton.tsx";

export default function Navbar() {

  const auth = useAuth();
  console.log(auth);
  return (
    <>
      <nav className={styles.main}>
        <section>
          <NavbarTitle />
        </section>
        <section className={styles.buttons}>
          {
            auth.isAuthenticated ? <NavCreateQuizButton/> : null
          }

          {/*<NavCreateQuizButton />*/}
          <ContactButton />
          <NoticeButton />
          {
            auth.isAuthenticated ? <NavLogoutButton /> : <NavLoginButton />
          }
        </section>
      </nav>
    </>
  )
}