import classes from "./QuizGrid.module.css"
import QuizItem from "./QuizItem.tsx";
import {useCallback, useEffect, useState} from "react";
import {SimpleQuizItem} from "../../../types/quiz.ts";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";
import {handleError} from "../../../global/error/error.ts";
import {useInView} from "react-intersection-observer";
import QuizSearchBar from "./QuizSearchBar.tsx";
import {Simulate} from "react-dom/test-utils";
import select = Simulate.select;

export enum SearchType {
  LATEST = "latest",
  POPULAR = "popular"
}

type SearchCondition = {
  nextPage: number;
  size: number;
  type: SearchType | null;
  isLastPage: boolean
}

const initSearchCondition: SearchCondition = {
  nextPage: 0,
  size: 20,
  type: null,
  isLastPage: false
}

export default function QuizGrid() {

  const [quizList, setQuizList] = useState<SimpleQuizItem[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [searchCondition, setSearchCondition] = useState<SearchCondition>(initSearchCondition);
  const [observeTrigger, inView] = useInView();

  useEffect(() => {
    if (loading) return; // 로딩중이라면 반환
    requestSimpleQuiz(searchCondition.nextPage, searchCondition.size, searchCondition.type);
  }, [inView])

  const requestSimpleQuiz = useCallback((nextPage: number, size: number, type: SearchType) => {
    setLoading(true); // 로딩 true
    axiosJwtInstance.get(`/api/quiz/list?page=${nextPage}&size=${size}&type=${type}`)
      .then((response) => {
        setQuizList(prev => [...prev, ...response.data])
        setSearchCondition(prev => ({...prev, nextPage: prev.nextPage + 1}));
        setLoading(false); // 로딩 false

        if (response.data.length < size) {
          // 만약 받은 데이터가 size보다 작으면 마지막 페이지라고 판단
          setSearchCondition(prev => ({...prev, isLastPage: true}));
        }
      })
      .catch((err) => {
        handleError(err);
      })
  }, [])

  const typeChange = (selectType: SearchType) => {
    console.log(`${selectType}가 선택됨.`)
    if (selectType === searchCondition.type) {
      setSearchCondition(prev => ({...prev, type: null}));
    } else {
      setSearchCondition(prev => ({...prev, type: selectType}));
    }
  }

  return (
    <>
      <QuizSearchBar type={searchCondition.type} typeChange={typeChange}/>
      <section className={classes.gridContainer}>
        {
          quizList.map((quiz, index) => {
            return <QuizItem key={`quiz_number_${index}`} item={quiz}/>
          })
        }
      </section>
      <div ref={observeTrigger} className={classes.gridObserver}>
        {
          loading ? <img src={"/img/icon/loading.gif"} alt={"loading"}/> : null
        }
      </div>
    </>

  )
}