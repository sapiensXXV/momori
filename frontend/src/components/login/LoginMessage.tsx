import styles from "./LoginMessage.module.css"

export default function LoginMessage() {
  return (
    <section className={styles.messageContainer}>
      <span className={styles.important}>로그인 하고</span>
      <span className={styles.normal}>퀴즈를 직접 만들어 보세요</span>
    </section>
  )
}