import {axiosJwtInstance} from "../../global/configuration/axios.ts";

export default function LinkButton({link, message}: { link: string, message: string }) {

  const onClick = (e: React.MouseEvent) => {
    e.preventDefault();
    axiosJwtInstance
      .get(`${link}`)
      .then(() => {
      })
      .catch(err =>
        console.log(err)
      )
  }


  return (
    <button onClick={(e) => onClick(e)}>
      <span>{message}</span>
    </button>
  )
}