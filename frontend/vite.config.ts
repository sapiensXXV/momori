import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [ react() ],
  resolve: {
    alias: [
      { find: '@', replacement: "src"},
      { find: '@components', replacement: "src/component"},
      { find: '@assets', replacement: "src/assets"},
      { find: '@global', replacement: "src/global"},
      { find: '@context', replacement: "src/context"},
    ]
  }
})
