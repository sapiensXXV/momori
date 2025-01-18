import axios from "axios";
import {BASE_URI} from "../../uri.ts";
import {getCookieValue} from "../cookie/cookie.ts";

export const axiosInstance = axios.create({
  baseURL: BASE_URI
});

export const axiosJwtInstance = axios.create ({
  baseURL: `${BASE_URI}/api`,
  withCredentials: true,
  headers: {
    "Authorization": `Bearer ${getCookieValue("jwtToken")}`
  }
})

