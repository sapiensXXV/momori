import { useAuth } from "../../context/AuthContext"
import QuizGrid from "../quiz/QuizGrid.tsx";

export default function Home() {

const auth = useAuth();


  return (
    <>
      <QuizGrid />
    </>
  )
}