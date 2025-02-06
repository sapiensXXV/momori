import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import {fileURLToPath} from "node:url";

// https://vite.dev/config/
export default defineConfig({
  plugins: [ react() ],
  resolve: {
    alias: [
      { find: '@', replacement: fileURLToPath(new URL('./', import.meta.url)) },
      { find: '@components', replacement: fileURLToPath(new URL('./src/components', import.meta.url)) },
      { find: '@public', replacement: fileURLToPath(new URL('./public', import.meta.url)) },
      { find: '@global', replacement: fileURLToPath(new URL('./src/global', import.meta.url)) },
      { find: '@context', replacement: fileURLToPath(new URL('./src/context', import.meta.url)) },
    ]
  }
})
