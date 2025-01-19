import {BASE_URI} from "../../../uri.ts";

export default function DiscordLoginButton() {

  function handleLoginClick() {
    console.log(`${BASE_URI}/oauth2/authorization/discord redirect`)
    window.location.href = BASE_URI + "/oauth2/authorization/discord";
  }

  return (
    <button className='discord login_button text_white' onClick={handleLoginClick}>
      <img src="/img/icon/discord.svg"/>
      <span>디스코드로 로그인</span>
    </button>
  )
}