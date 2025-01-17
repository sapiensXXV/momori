import {Link} from "react-router-dom";

export default function GithubLoginButton() {
  return (
    // <Link to={}>
      <button className='github_light login_button'>
        <img src="/img/icon/github.svg"/>
        <span>깃허브로 로그인</span>
      </button>
    // </Link>
  )
}