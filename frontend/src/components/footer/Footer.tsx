import styles from "./Footer.module.css"

export default function Footer() {
  return (
    <>
      <main className={styles.main}>
        <hr className={styles.footerDivider}/>
        <span className={styles.description}>© poolygo.com</span>
      </main>
    </>
  )
}