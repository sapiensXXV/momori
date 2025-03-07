import {useEffect} from "react";
import {axiosJwtInstance} from "../../global/configuration/axios.ts";

const AuthTestPage = () => {

  useEffect(() => {
    authTestRequest()
  }, []);

  const authTestRequest = async () => {
    const response = await axiosJwtInstance.get('/api/auth/auth-only-requet')
    console.log(response);
  }

  return (
    <>
      인증 테스트
    </>
  )
}

export default AuthTestPage