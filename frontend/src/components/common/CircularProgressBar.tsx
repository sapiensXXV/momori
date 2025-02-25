import {FC} from "react";
import classes from './CircularProgressBar.module.css';

type CircularProgressBarProps = {
  progress: number;
  circleWidth: number;
  children: JSX.Element;
}

const CircularProgressBar: FC<CircularProgressBarProps> = ({ progress, circleWidth, children} ) => {

  const radius: number = 85;
  const dashArray = radius * Math.PI * 2;
  const dashOffset = dashArray - (dashArray * progress / 100);

  return (
    <div style={{ position: "relative", width: circleWidth, height: circleWidth }}>
      <svg
        width={circleWidth}
        height={circleWidth}
        viewBox={`0 0 ${circleWidth} ${circleWidth}`}
      >
        {children}
        <circle
          cx={ circleWidth / 2}
          cy={circleWidth / 2}
          strokeWidth={"10px"}
          r={radius}
          className={classes.circleBackground}
        />

        <circle
          cx={circleWidth / 2}
          cy={circleWidth / 2}
          strokeWidth={"10px"}
          r={radius}
          className={classes.circleProgress}
          style={{
            strokeDasharray: dashArray,
            strokeDashoffset: dashOffset
          }}
          transform={`rotate(-90 ${circleWidth / 2} ${circleWidth / 2})`}
        />

      </svg>
      <div style={{
        position: "absolute",
        top: 0, left: 0,
        width: "100%", height: "100%",
        display: "flex",
        justifyContent: "center",
        alignItems: "center"
      }}>
        {children}
      </div>
    </div>
  );
}

export default CircularProgressBar;