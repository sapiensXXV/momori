import styles from "./LoginPage.module.css";
import LoginMessage from "./LoginMessage.tsx";
import OAuthLoginButtonContainer from "./button/OAuthLoginButtonContainer.tsx";

export default function LoginPage() {
  return (
    <>
      <section className={styles.main}>
        <LoginMessage />
        <OAuthLoginButtonContainer />
      </section>
    </>
  )
}