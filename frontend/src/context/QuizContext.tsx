import { createContext, useContext, useState } from "react";
import {QuizTypes} from "../components/quiz/types/Quiz.types.ts";
import {NewQuestionTypes} from "../types/question.ts";
import {initNewQuizMetadata, NewQuizMetadata} from "../types/quiz.ts";

interface QuizContextType<T extends NewQuestionTypes = NewQuestionTypes> {
  quizType: QuizTypes;
  setQuizType: React.Dispatch<React.SetStateAction<QuizTypes>>;
  draftCount: number;
  draftModal: boolean;
  setDraftModal: React.Dispatch<React.SetStateAction<boolean>>;
  setDraftCount: React.Dispatch<React.SetStateAction<number>>;
  metadata: NewQuizMetadata;
  setMetadata: React.Dispatch<React.SetStateAction<NewQuizMetadata>>;
  questions: T[];
  setQuestions: React.Dispatch<React.SetStateAction<T[]>>;
  hasDraft: boolean;
  setHasDraft: React.Dispatch<React.SetStateAction<boolean>>;
}

const QuizContext = createContext<QuizContextType<NewQuestionTypes> | undefined>(undefined);

export const QuizProvider = <T extends NewQuestionTypes = NewQuestionTypes>({ children }: { children: React.ReactNode }) => {
  const [quizType, setQuizType] = useState<QuizTypes>(QuizTypes.IMAGE_MCQ);
  const [draftCount, setDraftCount] = useState(0);
  const [draftModal, setDraftModal] = useState(false);
  const [metadata, setMetadata] = useState<NewQuizMetadata>(initNewQuizMetadata);
  const [questions, setQuestions] = useState<T[]>([]);
  const [hasDraft, setHasDraft] = useState<boolean>(false);

  return (
    <QuizContext.Provider value={{
      quizType,
      setQuizType,
      draftCount,
      setDraftCount,
      draftModal,
      setDraftModal,
      metadata,
      setMetadata,
      questions,
      hasDraft,
      setHasDraft,
      setQuestions: setQuestions as React.Dispatch<React.SetStateAction<NewQuestionTypes[]>>
    }}>
      {children}
    </QuizContext.Provider>
  );
};

export const useQuizContext = <T extends NewQuestionTypes = NewQuestionTypes>() => {
  const context = useContext(QuizContext);
  if (!context) {
    throw new Error("useQuizContext must be used within a QuizProvider");
  }
  return context as unknown as QuizContextType<T>;
};