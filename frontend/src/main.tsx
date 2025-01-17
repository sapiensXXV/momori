import {createRoot} from 'react-dom/client'
import './index.css'
import './global/css/color.css'
import './global/css/login_button.css'
import App from './App.tsx'

createRoot(document.getElementById('root')!).render(
  <App/>
)
