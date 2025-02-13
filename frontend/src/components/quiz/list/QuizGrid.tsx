import classes from "./QuizGrid.module.css"
import QuizItem from "./QuizItem.tsx";
import {useCallback, useEffect, useState} from "react";
import {SimpleQuizItem} from "../../../types/quiz.ts";
import {axiosJwtInstance} from "../../../global/configuration/axios.ts";
import {useInView} from "react-intersection-observer";
import QuizSearchBar from "./QuizSearchBar.tsx";
import {handleError} from "../../../global/error/error.ts";

export enum SearchType {
  LATEST = "latest",
  POPULAR = "popular"
}

type SearchCondition = {
  nextPage: number;
  searchTerm: string;
  size: number;
  type: SearchType | null;
  isLastPage: boolean
}

const initSearchCondition: SearchCondition = {
  nextPage: 0,
  searchTerm: "",
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
    if (loading || searchCondition.isLastPage) return; // 로딩 중이거나 마지막 페이지라면 로직을 수행하지 않는다.
    requestSimpleQuiz(searchCondition.nextPage, searchCondition.size, searchCondition.type, searchCondition.searchTerm);
  }, [inView]);

  useEffect(() => {
    initAndSearch();
  }, [searchCondition.type])

  const requestSimpleQuiz = useCallback((nextPage: number, size: number, type: SearchType | null, searchTerm: string) => {
    setLoading(true); // 로딩 true
    axiosJwtInstance.get(`/api/quiz/list?page=${nextPage}&size=${size}&type=${type}&searchTerm=${searchTerm}`)
      .then((response) => {
        if (nextPage === 0) {
          // 새로운 페이지가 0페이지 라면 새 검색이므로 기존 데이터를 무시하고 교체한다.
          setQuizList(response.data);
        } else {
          setQuizList(prev => [...prev, ...response.data])
        }
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
  }, [searchCondition])

  const initAndSearch = () => {
    // 다음 페이지를 0으로 설정하고, 마지막 페이지 변수로 거짓으로 설정한다.
    setSearchCondition(prev => ({...prev, nextPage: 0, isLastPage: false}));
    requestSimpleQuiz(0, searchCondition.size, searchCondition.type, searchCondition.searchTerm);
  }

  const typeChange = (selectType: SearchType) => {
    if (selectType === searchCondition.type) {
      setSearchCondition(prev => ({...prev, type: null}));
    } else {
      setSearchCondition(prev => ({...prev, type: selectType}));
    }
  }

  const searchInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    setSearchCondition(prev => ({...prev, searchTerm: e.target.value}))
  }

  const searchButtonClicked = () => {
    initAndSearch();
  }

  return (
    <>
      <QuizSearchBar
        type={searchCondition.type}
        typeChange={typeChange}
        searchInputChange={searchInputChange}
        searchButtonClicked={searchButtonClicked}
      />
      <section className={classes.gridContainer}>
        {
          quizList.map((quiz, index) => {
            return <QuizItem key={`quiz_number_${index}`} item={quiz}/>
          })
        }
      </section>
      {
        // 마지막 페이지라면 옵저버 트리거를 출력하지 않는다.
        searchCondition.isLastPage ? null : (
          <div ref={observeTrigger} className={classes.gridObserver}>
            {loading ? <img src="/img/icon/loading.gif" alt="loading" /> : null}
          </div>
        )
      }

    </>

  )
}