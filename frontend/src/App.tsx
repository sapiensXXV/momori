import Navbar from "./components/navbar/Navbar.tsx";
import Footer from "./components/footer/Footer.tsx";
import Home from "./components/home/Home.tsx";
import styles from "./App.module.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./components/login/LoginPage.tsx";
import NoticesPage from "./components/notices/NoticesPage.tsx";
import ContactPage from "./components/contact/ContactPage.tsx";
import {AuthProvider} from "./context/AuthContext.tsx";
import OAuth2Callback from "./components/login/callback/OAuth2Callback.tsx";
import NewQuiz from "./components/quiz/new/NewQuiz.tsx";
import {QuizProvider} from "./context/QuizContext.tsx";
import QuizPage from "./components/quiz/single/QuizPage.tsx";


function App() {

  return (
    <>
      <AuthProvider>
        <main className={styles.main}>
          <BrowserRouter>
            <Navbar/>
            <Routes>
              <Route path="/" element={<Home/>}/>
              <Route path="/login" element={<LoginPage/>}/>
              <Route path="/notices" element={<NoticesPage/>}/>
              <Route path="/contact" element={<ContactPage/>}/>
              <Route path="/auth/callback" element={<OAuth2Callback/>}/>
              <Route
                path="/quiz/new"
                element={<QuizProvider><NewQuiz /></QuizProvider>}
              />
              <Route path="/quiz/:quizId" element={<QuizPage />} />
            </Routes>
            <Footer/>
          </BrowserRouter>
        </main>
      </AuthProvider>

    </>
  )
}

export default App
