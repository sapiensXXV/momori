import classes from "./DraftButton.module.css"
import { React, FC, useState } from "react";

interface DraftButtonProps {
  pushDraft: () => void; // 임시 null
  pullDraft: () => void;// 임시 null
  count: number;
}

const DraftButton: FC<DraftButtonProps> = ({
  pushDraft,
  pullDraft,
  count
}) => {

  return (
    <>
      <div className={classes.draftButtonContainer}>
        <div className={classes.draftPushButton} onClick={() => pushDraft()}>임시저장</div>
        <div className={classes.draftButtonDivider}></div>
        <div className={classes.draftPullButton} onClick={() => pullDraft()}>{count}</div>
      </div>

    </>
  )
}

export default DraftButton;