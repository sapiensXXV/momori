import {createRoot} from 'react-dom/client'
import './index.css'
import './global/css/color.css'
import './global/css/login_button.css'
import './styles/global.css'
import './styles/variable.css'
import './styles/utilities.css'
import './styles/color.css'
import App from './App.tsx'

createRoot(document.getElementById('root')!).render(
  <App/>
)
