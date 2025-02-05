import axios from "axios";
import {BASE_URI} from "../../uri.ts";
import {getCookieValue} from "../cookie/cookie.ts";

export const axiosInstance = axios.create({
  baseURL: BASE_URI
});



// export const axiosJwtInstance = axios.create ({
//   baseURL: `${BASE_URI}`,
//   withCredentials: true,
//   headers: {
//     "Authorization": `Bearer ${getCookieValue("jwtToken")}`
//   }
// })

export const axiosJwtInstance = axios.create({
  baseURL: `${BASE_URI}`,
  withCredentials: true,
});

axiosJwtInstance.interceptors.request.use((config) => {
  const jwtToken = getCookieValue("jwtToken");
  if (jwtToken) {
    config.headers.Authorization = `Bearer ${jwtToken}`;
  }
  return config;
});
