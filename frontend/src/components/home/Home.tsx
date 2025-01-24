import { useAuth } from "../../context/AuthContext"
import QuizGrid from "../quiz/QuizGrid.tsx";

export default function Home() {

const auth = useAuth();

console.log(`provider=${auth.provider}, roles=${auth.roles}, name=${auth.name}, isAuthenticated=${auth.isAuthenticated}`)

  return (
    <>
      <QuizGrid />
    </>
  )
}