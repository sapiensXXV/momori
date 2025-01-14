import Navbar from "./components/navbar/Navbar.tsx";
import Footer from "./components/footer/Footer.tsx";
import Home from "./components/home/Home.tsx";
import styles from "./App.module.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {

  return (
    <>
      <main className={styles.main}>
        <BrowserRouter>
          <Navbar/>
          <Routes>
            <Route path="/" element={ <Home/> } />
          </Routes>
          <Footer/>
        </BrowserRouter>
      </main>
    </>
  )
}

export default App
