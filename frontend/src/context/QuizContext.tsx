import { createContext, useContext, useState } from "react";
import {QuizTypes} from "../components/quiz/types/Quiz.types.ts";
import {QuestionTypes} from "../types/question.ts";
import {initNewQuizMetadata, NewQuizMetadata} from "../types/quiz.ts";

interface QuizContextType<T extends QuestionTypes = QuestionTypes> {
  quizType: QuizTypes;
  setQuizType: React.Dispatch<React.SetStateAction<QuizTypes>>;
  draftCount: number;
  setDraftCount: React.Dispatch<React.SetStateAction<number>>;
  metadata: NewQuizMetadata;
  setMetadata: React.Dispatch<React.SetStateAction<NewQuizMetadata>>;
  questions: T[];
  setQuestions: React.Dispatch<React.SetStateAction<T[]>>;
}

const QuizContext = createContext<QuizContextType<QuestionTypes> | undefined>(undefined);

export const QuizProvider = <T extends QuestionTypes = QuestionTypes>({ children }: { children: React.ReactNode }) => {
  const [quizType, setQuizType] = useState<QuizTypes>(QuizTypes.IMAGE_MCQ);
  const [draftCount, setDraftCount] = useState(0);
  const [metadata, setMetadata] = useState<NewQuizMetadata>(initNewQuizMetadata);
  const [questions, setQuestions] = useState<T[]>([]);

  return (
    <QuizContext.Provider value={{
      quizType,
      setQuizType,
      draftCount,
      setDraftCount,
      metadata,
      setMetadata,
      questions,
      setQuestions: setQuestions as React.Dispatch<React.SetStateAction<QuestionTypes[]>>
    }}>
      {children}
    </QuizContext.Provider>
  );
};

export const useQuizContext = <T extends QuestionTypes = QuestionTypes>() => {
  const context = useContext(QuizContext);
  if (!context) {
    throw new Error("useQuizContext must be used within a QuizProvider");
  }
  return context as unknown as QuizContextType<T>;
};