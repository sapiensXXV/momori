import { useAuth } from "../../context/AuthContext"

export default function Home() {

const auth = useAuth();

console.log(`provider=${auth.provider}, roles=${auth.roles}, name=${auth.name}, isAuthenticated=${auth.isAuthenticated}`)

  return (
    <>
      this is home page
    </>
  )
}