import styled from 'styled-components'

export const ImageMcqFormMain = styled.section`
  display: flex;
  flex-direction: column;
  background-color: #f6f6f6;
  padding: 10px;
  width: 100%;
  row-gap: 5px;
  border-radius: 5px;
`;

export const QuestionAddButton = styled.button`
    display: flex;
    border: none;
    padding: 5px 10px;
    height: 35px;
    cursor: pointer;
    align-items: center;
    justify-content: center;
    gap: 5px;
    background-color: #ffc935;
    border-radius: 3px;
    transition: transform 0.2s ease-in-out, background-color 0.2s ease-in-out;

    &:hover {
        transform: scale(1.03);
    }

    img {
        height: 100%;
    }

    span {
        font-family: 'Pretendard-Bold';
        font-size: 14px;
    }
`;

export const Question = styled.div`
  display: flex;
  gap: 5px;
  align-items: flex-start;
`;

export const FileInputContainer = styled.div`
  display: flex;
  gap: 5px;
  align-items: center;
`;

export const HiddenInput = styled.input`
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
`;

export const CustomUploadBox = styled.label<{ $isLoading: boolean }>`
  display: inline-block;
  width: 80px;
  height: 80px;
  border: 2px dashed #ccc;
  border-radius: 8px;
  background-color: #f8f9fa;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  text-align: center;
  pointer-events: ${({ $isLoading }) => $isLoading ? 'none' : 'auto'};

  &:hover,
  ${HiddenInput}:focus + & {
    transform: scale(1.08);
    border-color: #ffd66d;
    background-color: #ffc935;
  }
`;

export const UploadIcon = styled.img`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 80%;
  height: 80%;
  pointer-events: none;
  transition: filter 0.3s ease;
`;

export const ChoiceAddButton = styled.button`
  border: none;
  width: 100px;
  height: 80px;
  border-radius: 5px;
  background-color: #ffe6a6;
  cursor: pointer;
  font-family: 'Pretendard-Bold';
  transition: transform 0.2s ease-in-out;

  &:hover {
    transform: scale(1.08);
  }
`;

export const ChoiceContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 4px;
`;

export const Choice = styled.div`
  display: flex;
  align-items: center;
  gap: 5px;

  span {
    width: 25px;
  }

  input[type="checkbox"] {
    width: 15px;
    height: 15px;
    border: none;
  }

  input[type="text"] {
    flex: 1;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 3px;
  }
`;

