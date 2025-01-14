import styles from "./OAuthLoginButtonContainer.module.css"
import KakaoLoginButton from "./KakaoLoginButton.tsx";
import DiscordLoginButton from "./DiscordLoginButton.tsx";

export default function OAuthLoginButtonContainer() {
  return (
    <section className={styles.container}>
      <KakaoLoginButton />
      <DiscordLoginButton />
    </section>
  )
}