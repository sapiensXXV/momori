import classes from './PercentageBar.module.css'
import {FC, useEffect, useState} from "react";

type PercentageBarProps = {
  percentage: number // 0~100
}

const PercentageBar: FC<PercentageBarProps> = ({ percentage }) => {

  const [animatingPercentage, setAnimatingPercentage] = useState<number>(0);

  useEffect(() => {
    const timer = setTimeout(() => {
      setAnimatingPercentage(percentage)
    }, 200); // ms단위

    return () => clearTimeout(timer);
  }, []);

  return (
    <>
      <div className={classes.percentageBarContainer}>
        <div
          className={classes.percentageBar}
          style={{width: `${animatingPercentage}%`}}
        >
          {/* 원한다면 퍼센티지 텍스트를 표시할 수 있습니다. */}

        </div>

      </div>
      <span className={classes.percentageText}>{animatingPercentage}%</span>
    </>

  );
}

export default PercentageBar;