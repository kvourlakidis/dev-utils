function getClosedTimesTotal()
{
    hours = 0;
    let table = document.getElementsByClassName("com-ibm-team-rtc-foundation-web-ui-gadgets-table-Table")[0];
    for (i=2;i<table.childElementCount;i++)
    {
        let story = table.childNodes[i];
        let completed = story.lastChild;
        let tasks = completed.getElementsByClassName("com-ibm-team-apt-web-ui-internal-common-viewer-plan-board-TaskNote");
        for (j=0;j<tasks.length;j++)
        {
            let task = tasks[j];
            let effort =  task.querySelectorAll('div[title="Effort Tracking"]')[0];
            let summary = task.querySelectorAll('div[title="Summary"]')[0];
            let taskName = summary.children[0].title.substring(9);
            let timeTakenString = effort.children[0].title;
            console.log("Task: " + taskName + "; Time taken: " + timeTakenString.substring(17));
            let timeTakenArray = timeTakenString.split(" ");
            let number = timeTakenArray[2];
            let unit = timeTakenArray[3];
            if ((number !== undefined) && (unit !== undefined) && (!isNaN(number)))
            {
                if (unit.startsWith("hour")) 
                {
                    hours = hours + parseInt(number);
                } 
                else if (unit.startsWith("day"))
                {
                    hours = hours + (parseInt(number) * 8);
                }
                else if (unit.startsWith("minute"))
                {
                    hours = hours + (parseInt(number) / 60);
                }
            }
        }
    }
    console.log("");
    console.log("Total closed tasks on the board time taken (hours): " + hours);
    alert("Closed tasks total: " + Math.round(hours) + " hours.");
}