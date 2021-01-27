export interface Newsletter{
  title: string;
  content: string;
  dateString: string;
}

export interface NewsletterFullModel{
  
    id: number,
    title: string,
    content: string,
    culturalOffering: {
        id: number,
        name: string,
        categoryType: {
            name: string
        }
    }

}
