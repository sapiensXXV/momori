import { createContext, useContext, useState } from "react";
import {QuizTypes} from "../components/quiz/types/Quiz.types.ts";
import {QuestionTypes} from "../types/question.ts";

interface QuizContextType {
  quizType: QuizTypes;
  setQuizType: (type: QuizTypes) => void;
  questions: QuestionTypes[];
  setQuestions: React.Dispatch<React.SetStateAction<QuestionTypes[]>>;
}

const QuizContext = createContext<QuizContextType | undefined>(undefined);

export const QuizProvider = ({ children }: { children: React.ReactNode }) => {
  const [quizType, setQuizType] = useState<QuizTypes>(QuizTypes.IMAGE_MCQ);
  const [questions, setQuestions] = useState<QuestionTypes[]>([]);

  return (
    <QuizContext.Provider value={{ quizType, setQuizType, questions, setQuestions }}>
      {children}
    </QuizContext.Provider>
  );
};

export const useQuizContext = () => {
  const context = useContext(QuizContext);
  if (!context) throw new Error("useQuizContext must be used within a QuizProvider");
  return context;
};