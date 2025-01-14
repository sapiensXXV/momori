import styles from "./Navbar.module.css"
import NavLoginButton from "./NavTextButton.tsx";
import NoticeButton from "./NoticeButton.tsx";
import NavbarTitle from "./NavbarTitle.tsx";
import ContactButton from "./ContactButton.tsx";

export default function Navbar() {
  return (
    <>
      <nav className={styles.main}>
        <section>
          <NavbarTitle />
        </section>
        <section className={styles.buttons}>
          <ContactButton />
          <NoticeButton />
          <NavLoginButton />
        </section>
      </nav>
    </>
  )
}