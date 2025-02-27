interface AlertStyles {
  alertContainer: React.CSSProperties;
  alertBox: React.CSSProperties;
  message: React.CSSProperties;
  closeButton: React.CSSProperties;
  hidden: React.CSSProperties;
  entering: React.CSSProperties;
  visible: React.CSSProperties;
  exiting: React.CSSProperties;
}

// CSS 스타일
export const alertStyles: AlertStyles = {
  alertContainer: {
    position: 'fixed',
    top: '16px',
    left: '0',
    right: '0',
    zIndex: '9999',
    display: 'flex',
    justifyContent: 'center',
    pointerEvents: 'none',
  },
  alertBox: {
    backgroundColor: 'white',
    boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
    borderRadius: '6px',
    padding: '16px',
    maxWidth: '400px',
    border: '1px solid #e2e8f0',
    pointerEvents: 'auto',
    display: 'flex',
    alignItems: 'flex-start',
    transition: 'transform 0.5s ease, opacity 0.5s ease',
  },
  message: {
    flexGrow: 1,
    marginLeft: '12px',
    fontSize: '14px',
    color: '#333',
  },
  closeButton: {
    marginLeft: '16px',
    background: 'none',
    border: 'none',
    cursor: 'pointer',
    color: '#718096',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    padding: '4px',
  },
  hidden: {
    display: 'none',
  },
  entering: {
    transform: 'translateY(0)',
    opacity: '0',
  },
  visible: {
    transform: 'translateY(0)',
    opacity: '1',
  },
  exiting: {
    transform: 'translateY(-100%)',
    opacity: '0',
  }
};