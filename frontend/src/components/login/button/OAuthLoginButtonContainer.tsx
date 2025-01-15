import styles from "./OAuthLoginButtonContainer.module.css"
import KakaoLoginButton from "./KakaoLoginButton.tsx";
import DiscordLoginButton from "./DiscordLoginButton.tsx";
import NaverLoginButton from "./NaverLoginButton.tsx";

export default function OAuthLoginButtonContainer() {
  return (
    <section className={styles.container}>
      <NaverLoginButton />
      <KakaoLoginButton />
      <DiscordLoginButton />
    </section>
  )
}