import styles from "./DiscordLoginButton.module.css";

export default function DiscordLoginButton() {
  return (
    <button className={`discord ${styles.button}`}>
      <img src="/img/icon/discord.svg"/>
      <span>디스코드로 로그인</span>
    </button>
  )
}