import classes from './QuizSearchBar.module.css'
import React, {FC} from "react";
import {SearchType} from "./QuizGrid.tsx";

type QuizSearchBarProps = {
  // type: SearchType;
  typeChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
}

const QuizSearchBar: FC<QuizSearchBarProps> = ({ typeChange }) => {
  return (
    <>
      <section className={classes.searchContainer}>
        <div className={`${classes.searchConditionButton} common-button`}>최신순</div>
        <div className={`${classes.searchConditionButton} common-button`}>인기순</div>
        <input className={`${classes.searchInput} common-input-sm`} type={"text"} placeholder={"퀴즈를 검색하세요"}/>
        <div className={`${classes.searchButton} common-button`}>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
               stroke="currentColor" className="size-6">
            <path stroke-linecap="round" stroke-linejoin="round"
                  d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"/>
          </svg>

        </div>
      </section>
    </>
  )
}

export default QuizSearchBar;