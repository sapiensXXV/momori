import {useState} from "react";
import CustomAlert from "./CustomAlert.tsx";

interface Alert {
  id: number;
  message: string;
  duration: number;
}

interface AlertManagerReturn {
  showAlert: (message: string, duration?: number) => number;
  AlertContainer: React.FC;
}

export const useAlertManager = (): AlertManagerReturn => {
  const [alerts, setAlerts] = useState<Alert[]>([]);

  const showAlert = (message: string, duration: number = 3000): number => {
    const id = Date.now();
    setAlerts(prevAlerts => [...prevAlerts, { id, message, duration }]);
    return id;
  };

  const closeAlert = (id: number): void => {
    setAlerts(prevAlerts => prevAlerts.filter(alert => alert.id !== id));
  };

  const AlertContainer: React.FC = () => (
    <>
      {alerts.map(alert => (
        <CustomAlert
          key={alert.id}
          message={alert.message}
          duration={alert.duration}
          isVisible={true}
          onClose={() => closeAlert(alert.id)}
        />
      ))}
    </>
  );

  return { showAlert, AlertContainer };
};