import React, {useState} from "react";
import {ImageMcqQuestion} from "../../types/ImageMcqQuestion.types.ts";
import axios from "axios";
import {BASE_URI} from "../../../../uri.ts";
import {ImageUrlResponse} from "../../types/ImageUrlResponse.ts";
import {compressImage} from "../../../../util/image/ImageCompress.ts";

export default function ImageMcqForm() {
  const [questions, setQuestions] = useState<ImageMcqQuestion[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const imageUploader = async (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
    e.preventDefault();
    if (!e.target.value) return;

    // 1. 파일 존재 여부 체크 (안전한 접근)
    if (!e.target.files || e.target.files.length === 0) {
      console.log("No file selected");
      return;
    }

    try {

      const compressedFile = await compressImage(e.target.files[0]);

      setIsLoading(true);
      const formData = new FormData();
      formData.append("image", compressedFile);

      //서버측에 이미지 전달
      const response = await axios.post<{ imageUrl: string; }>(
        `${BASE_URI}/api/quiz/draft/image`,
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
          }
        }
      );
      const data: ImageUrlResponse = response.data;
      const imageUrl = data.imageUrl;
      console.log(response);

      const copy: ImageMcqQuestion[] = [...questions];
      copy[index].imageUrl = imageUrl;
      setQuestions(copy);
      console.log(`questions: ${JSON.stringify(copy)}`);
    } catch (error) {
      console.error("Upload failed: ", error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleAddQuestion = (e: React.MouseEvent) => {
    e.preventDefault();
    const newQuestion: ImageMcqQuestion = {
      imageUrl: null,
      choices: [],
      answers: [],
    }
    setQuestions([...questions, newQuestion]);
  }

  const handleAddChoice = (
    e: React.MouseEvent,
    index: number
  ) => {
    e.preventDefault();
    const copyQuestions = [...questions];
    const newChoice = { content: "", isAnswer: false };
    copyQuestions[index].choices.push(newChoice); // 새로운 선택지 값 추가
    setQuestions(copyQuestions);
  }

  const handleChoiceValueChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    qi: number,
    ci: number
  ) => {
    e.preventDefault();
    const copyQuestions = [...questions];
    copyQuestions[qi].choices[ci] = { content: e.target.value, isAnswer: false }
    setQuestions(copyQuestions);
  }

  const handleChoiceAnswerCheck = (
    e: React.ChangeEvent<HTMLInputElement>,
    qi: number,
    ci: number
  ) => {
    e.preventDefault();
    const copyQuestions = [...questions];
    copyQuestions[qi].choices[ci].isAnswer = !copyQuestions[qi].choices[ci].isAnswer;
    setQuestions(copyQuestions);
  }

  return (
    <section>
      <button onClick={(e) => handleAddQuestion(e)}>문제 추가하기</button>
      {questions.map((question, qi) => (
        <div key={`question_${qi}`}>
          <input
            type="file"
            accept="image/*"
            onChange={(e) => imageUploader(e, qi)}
          />
          <button onClick={(e) => handleAddChoice(e, qi)}>선택지 추가</button>

          {question.choices.map((choice, ci) => (
            <div key={`choice_${qi}_${ci}`}>
              <input
                type="checkbox"
                checked={question.choices[ci].isAnswer}
                onChange={(e) => handleChoiceAnswerCheck(e, qi, ci)}
              />
              <input
                type="text"
                placeholder="선택지를 입력하세요"
                value={choice.content}
                onChange={(e) => handleChoiceValueChange(e, qi, ci)}
              />
            </div>
          ))}
        </div>
      ))}
    </section>
  )
}