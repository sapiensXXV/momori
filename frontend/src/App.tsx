import Navbar from "./components/navbar/Navbar.tsx";
import Footer from "./components/footer/Footer.tsx";
import Home from "./components/home/Home.tsx";
import styles from "./App.module.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./components/login/LoginPage.tsx";
import NoticesPage from "./components/notices/NoticesPage.tsx";
import ContactPage from "./components/contact/ContactPage.tsx";
import LinkButton from "./components/chore/LinkButton.tsx";
import {BASE_URI} from "./uri.ts";

function App() {

  return (
    <>
      <main className={styles.main}>
        <BrowserRouter>
          <Navbar/>
          <Routes>
            <Route path="/" element={ <Home /> } />
            <Route path="/login" element={ <LoginPage /> } />
            <Route path="/notices" element={ <NoticesPage /> } />
            <Route path="/contact" element={ <ContactPage /> } />
          </Routes>
          <Footer/>
          <LinkButton link={`${BASE_URI}/api/admin`} message='ADMIN Button' />
          <LinkButton link={`${BASE_URI}/api/user`} message='USER Button' />
          <LinkButton link={`${BASE_URI}/api/everyone`} message='EVERYONE Button' />
        </BrowserRouter>

      </main>

    </>
  )
}

export default App
